package org.gokareless.examples.graphql.datafetchers;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

public class EncodableDataFetcher implements DataFetcher {

    @Override
    public Object get(DataFetchingEnvironment environment) {
        return "Yeah";
    }
}
