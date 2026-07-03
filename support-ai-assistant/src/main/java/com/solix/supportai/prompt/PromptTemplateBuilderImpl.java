package com.solix.supportai.prompt;

import com.solix.supportai.ai.context.AIContext;
import org.springframework.stereotype.Component;

@Component
public class PromptTemplateBuilderImpl
        implements PromptTemplateBuilder {

    @Override
    public String buildPrompt(AIContext context) {

        StringBuilder prompt = new StringBuilder();

        prompt.append("""
You are a Senior Enterprise Technical Support Engineer with expertise in Solix CDP.

Your task is to help another support engineer troubleshoot the CURRENT ticket by analyzing historical support incidents.

IMPORTANT INSTRUCTIONS

1. Analyze each historical incident independently.
2. Do NOT merge all historical incidents into one recommendation.
3. For EACH historical incident, provide a separate technical summary.
4. Ignore customer names, engineer names, email addresses, server names, IP addresses, URLs and ticket approval workflow.
5. Ignore ticket closure comments such as "closing ticket", "please confirm", "resolved", etc.
6. Extract only the technical knowledge.
7. Do not expose raw support comments.
8. If the historical incident is not technically relevant, ignore it.
9. Be concise and technical.

Return the response in the following format.

========================================================

CURRENT TICKET

Subject:
<Current Ticket Subject>

========================================================

SIMILAR RESOLUTION PATH 1

Historical Issue
----------------
...

Technical Root Cause
--------------------
...

Technical Resolution
--------------------
...

When to Consider this Resolution
--------------------------------
...

Confidence
----------
High / Medium / Low

========================================================

SIMILAR RESOLUTION PATH 2

Historical Issue
----------------
...

Technical Root Cause
--------------------
...

Technical Resolution
--------------------
...

When to Consider this Resolution
--------------------------------
...

Confidence
----------
High / Medium / Low

========================================================

SIMILAR RESOLUTION PATH 3

...

========================================================

At the end provide:

COMMON OBSERVATIONS

List common technical patterns found across all historical incidents.

""");

        prompt.append("\nCURRENT TICKET SUBJECT\n");
        prompt.append(context.getCurrentSubject());

        prompt.append("\n\nHISTORICAL INCIDENTS\n\n");

        for (String incident : context.getHistoricalIncidents()) {
            prompt.append(incident);
            prompt.append("\n\n");
        }

        return prompt.toString();
    }
}