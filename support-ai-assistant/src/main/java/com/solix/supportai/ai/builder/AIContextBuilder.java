package com.solix.supportai.ai.builder;

import com.solix.supportai.ai.context.AIContext;

public interface AIContextBuilder {

    AIContext build(String ticketId);

}