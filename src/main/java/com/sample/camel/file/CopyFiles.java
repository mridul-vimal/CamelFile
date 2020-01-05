package com.sample.camel.file;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.log4j.Logger;


public class CopyFiles {
    static Logger logger = Logger.getLogger(CopyFiles.class);
    public static void main(String[] args) {
        CamelContext context = new DefaultCamelContext();
        try {
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("file:data/input?noop=true")
                            .to("log:?level=INFO&showBody=true&showHeaders=true")
                            .to("file:data/output");
                }
            });

            context.start();
            Thread.sleep(3000);
            context.stop();
        } catch (Exception e) {
            System.out.println("Exception "+e);
            e.printStackTrace();
        }

    }
}
