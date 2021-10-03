package com.file_attente;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BoxFactoryTest {

    @Test
    public void getBox() {

        assertEquals(null, BoxFactory.getBox(""));
        assertEquals("MonitoringBox", BoxFactory.getBox(BoxFactory.MONITORING).getClass().getSimpleName());
        assertEquals("CheckingBox", BoxFactory.getBox(BoxFactory.CHECKING).getClass().getSimpleName());
        assertEquals("TicketBox", BoxFactory.getBox(BoxFactory.TICKET).getClass().getSimpleName());

    }
}
