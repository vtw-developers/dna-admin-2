package com.vtw.dna.integration.shell;

import org.jline.utils.AttributedString;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.jline.PromptProvider;

@Configuration
public class ShellApplicationConfiguration implements PromptProvider {

    @Override
    public final AttributedString getPrompt() {
        return new AttributedString("edu:>");
    }

}
