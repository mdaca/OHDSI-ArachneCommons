package com.odysseusinc.arachne.execution_engine_common.util;

import com.odysseusinc.arachne.commons.types.DBMSType;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class ConnectionParamsParser {

    public static ConnectionParams parse(DBMSType dbmsType, String connString) {

        ConnectionParams dto = getParser(dbmsType).parseParams(connString);
        dto.setConnectionString(connString);
        dto.setDbms(dbmsType.getOhdsiDB());
        return dto;
    }

    private static ConnectionStringParser getParser(DBMSType dbmsType) {

        switch (dbmsType) {
            case POSTGRESQL:
            case MS_SQL_SERVER:
            case NETEZZA:
            case PDW:
                return new GenericParser();
            case REDSHIFT:
                return new RedshiftParser();
            case ORACLE:
                return new OracleParser();
            case IMPALA:
                return new ImpalaParser();
            default:
                return connString -> {
                    ConnectionParams dto = new ConnectionParams();
                    dto.setConnectionString(connString);
                    return dto;
                };
        }
    }

    interface ConnectionStringParser {
        ConnectionParams parseParams(String connString);
    }

    static class GenericParser implements ConnectionStringParser {

        private Pattern pattern = Pattern.compile("^jdbc:\\w+(:\\w+)?://([\\w.\\d-]+)(:\\d+)?(/(\\w+))?[?;]?(.*)");

        public ConnectionParams parseParams(String connString) {
            ConnectionParams dto = new ConnectionParams();
            Matcher matcher = pattern.matcher(connString);
            if (matcher.matches() && matcher.groupCount() == 6){
                dto.setServer(matcher.group(2));
                dto.setPort(matcher.group(3));
                dto.setSchema(matcher.group(5));
                String paramString = matcher.group(6); //params
                dto.setExtraSettings(paramString);
                if (Objects.nonNull(paramString)) {
                    List<String> paramValues = Arrays.asList(paramString.split("[&;]"));
                    Map<String, String> params = paramValues.stream()
                            .filter(v -> Objects.nonNull(v) && v.contains("="))
                            .map(v -> v.split("=")).collect(Collectors.toMap(x -> x[0], x -> x[1]));
                    parseCredentials(dto, params);
                }
            }
            return dto;
        }

        protected void parseCredentials(ConnectionParams dto, Map<String, String> params) {
            dto.setUser(params.getOrDefault(getUserParamName(), dto.getUser()));
            dto.setPassword(params.getOrDefault(getPasswordParamName(), dto.getPassword()));
        }

        protected String getUserParamName() {
            return "user";
        }

        protected String getPasswordParamName() {
            return "password";
        }
    }

    static class ImpalaParser extends RedshiftParser {
        @Override
        protected void parseCredentials(ConnectionParams dto, Map<String, String> params) {
            try {
                Integer authMech = Integer.valueOf(params.getOrDefault("AuthMech", "0"));
                if (3 == authMech) {
                    super.parseCredentials(dto, params);
                }
            }catch (NumberFormatException ignored){
            }
        }
    }

    static class RedshiftParser extends GenericParser {
        @Override
        protected String getUserParamName() {
            return "UID";
        }

        @Override
        protected String getPasswordParamName() {
            return "PWD";
        }
    }

    static class OracleParser extends GenericParser {

        private Pattern pattern = Pattern.compile("^jdbc:oracle:\\w+:((\\w+)/(\\w+))?@(\\S+)");

        @Override
        public ConnectionParams parseParams(String connString) {
            ConnectionParams dto = new ConnectionParams();
            Matcher matcher = pattern.matcher(connString);
            if (matcher.matches() && matcher.groupCount() == 4) {
                dto.setUser(matcher.group(2));
                dto.setPassword(matcher.group(3));
                dto.setServer(matcher.group(4));
            }
            return dto;
        }
    }

}
