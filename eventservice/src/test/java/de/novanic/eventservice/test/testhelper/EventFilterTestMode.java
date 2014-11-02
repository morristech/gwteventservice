/*
 * GWTEventService
 * Copyright (c) 2014 and beyond, GWTEventService Committers
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 * Other licensing for GWTEventService may also be possible on request.
 * Please view the license.txt of the project for more information.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package de.novanic.eventservice.test.testhelper;

import de.novanic.eventservice.client.event.Event;
import de.novanic.eventservice.client.event.filter.EventFilter;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author sstrohschein
 * <br>Date: 17.08.2008
 * <br>Time: 21:50:42
 *
 * Filters every second event.
 */
public class EventFilterTestMode implements EventFilter
{
    private AtomicBoolean isExcluded;

    public EventFilterTestMode() {
        isExcluded = new AtomicBoolean(true);
    }

    public boolean match(Event anEvent) {
        //atomic toggle (every second event is filtered)
        boolean theOldIsExcluded = isExcluded.get();
        while(!(isExcluded.compareAndSet(theOldIsExcluded, !theOldIsExcluded))) {}
        return !theOldIsExcluded;
    }
}