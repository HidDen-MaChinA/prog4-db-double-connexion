package com.web.learningBackEnd.Configuration.DatasourceConf.Datasource;

public class DatabaseContext {
        private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

        public static void setDatabaseKey(String databaseKey) {
            contextHolder.set(databaseKey);
        }

        public static String getDatabaseKey() {
            return contextHolder.get();
        }

        public static void clearDatabaseKey() {
            contextHolder.remove();
        }
}
