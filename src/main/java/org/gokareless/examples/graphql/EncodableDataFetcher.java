package org.gokareless.examples.graphql;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

public class EncodableDataFetcher implements DataFetcher {

    @Override
    public Object get(DataFetchingEnvironment environment) {
        return "Yeah";
    }
}
