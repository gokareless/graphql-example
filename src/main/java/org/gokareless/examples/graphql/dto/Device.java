package org.gokareless.examples.graphql.dto;

public enum Device {
    DESKTOP {
        @Override
        public boolean isDesktop() {
            return true;
        }

        @Override
        public boolean isMobile() {
            return false;
        }
    },
    MOBILE {
        @Override
        public boolean isDesktop() {
            return false;
        }

        @Override
        public boolean isMobile() {
            return true;
        }
    };

    public abstract boolean isDesktop();
    public abstract boolean isMobile();
}
