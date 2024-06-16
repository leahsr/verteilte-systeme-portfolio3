package de.fhws.fiw.fds.partneruniversitymanagement;

import de.fhws.fiw.fds.sutton.server.AbstractStart;

public class Start extends AbstractStart {

    public static final String CONTEXT_PATH = PathUrls.CONTEXT_PATH;

    public static void main(String[] args) throws Exception {
        System.out.println("Käse");
        new Start().startTomcat();
    }

    @Override
    protected String contextPath() {
        return CONTEXT_PATH;
    }
}

