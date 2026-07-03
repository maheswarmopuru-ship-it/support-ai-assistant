package com.solix.supportai.knowledge;

import com.solix.supportai.model.SupportTicket;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KnowledgeAnalyzerImpl implements KnowledgeAnalyzer {

    @Override
    public String prepareKnowledge(List<SupportTicket> tickets) {

        StringBuilder builder = new StringBuilder();

        int count = 1;

        for (SupportTicket ticket : tickets) {

            builder.append("Historical Ticket ")
                    .append(count++)
                    .append("\n");

            builder.append("Subject : ")
                    .append(ticket.getSubject())
                    .append("\n");

            builder.append("Support Comments : ")
                    .append(ticket.getSupportComments())
                    .append("\n\n");
        }

        return builder.toString();
    }

}