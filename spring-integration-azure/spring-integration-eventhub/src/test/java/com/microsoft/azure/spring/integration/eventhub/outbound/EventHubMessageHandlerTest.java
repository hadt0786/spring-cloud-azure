/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for
 * license information.
 */

package com.microsoft.azure.spring.integration.eventhub.outbound;

import com.microsoft.azure.spring.integration.core.AzureMessageHandler;
import com.microsoft.azure.spring.integration.core.api.PartitionSupplier;
import com.microsoft.azure.spring.integration.eventhub.api.EventHubOperation;
import com.microsoft.azure.spring.integration.test.support.MessageHandlerTest;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.messaging.Message;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EventHubMessageHandlerTest extends MessageHandlerTest<EventHubOperation> {

    @Before
    @Override
    @SuppressWarnings("unchecked")
    public void setUp() {
        this.future.complete(null);
        this.sendOperation = mock(EventHubOperation.class);
        when(this.sendOperation.sendAsync(eq(this.destination), isA(Message.class), isA(PartitionSupplier.class)))
                .thenReturn(future);
        this.handler = new AzureMessageHandler(this.destination, this.sendOperation);
    }

}
