package com.solix.supportai.prompt;

import com.solix.supportai.ai.context.AIContext;

public interface PromptTemplateBuilder {

    String buildPrompt(AIContext context);

}