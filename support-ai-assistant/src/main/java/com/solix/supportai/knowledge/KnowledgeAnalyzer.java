package com.solix.supportai.knowledge;

import com.solix.supportai.model.SupportTicket;

import java.util.List;

public interface KnowledgeAnalyzer {

    String prepareKnowledge(List<SupportTicket> tickets);

}