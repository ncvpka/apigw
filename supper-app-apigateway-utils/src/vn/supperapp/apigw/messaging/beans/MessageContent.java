package vn.supperapp.apigw.messaging.beans;

import com.google.common.collect.ImmutableMap;
import vn.supperapp.apigw.utils.CommonUtils;
import vn.supperapp.apigw.utils.enums.Language;

import java.util.HashMap;
import java.util.Map;

public class MessageContent {
    private final Map<String, String> titles;
    private final Map<String, String> replacements;
    private final Map<String, String> msgTemplates;
    private final Map<String, String> contents;

    private MessageContent(Builder builder) {
        this.titles = builder.titles.isEmpty() ? null : ImmutableMap.copyOf(builder.titles);
        this.replacements = builder.replacements.isEmpty() ? null : ImmutableMap.copyOf(builder.replacements);
        this.msgTemplates = builder.msgTemplates.isEmpty() ? null : ImmutableMap.copyOf(builder.msgTemplates);

        if (this.msgTemplates != null) {
            this.contents = new HashMap<>();
            for (Map.Entry<String, String> it : this.msgTemplates.entrySet()) {
                String key = it.getKey();
                String value = it.getValue();
                if (!CommonUtils.isNullOrEmpty(value) && this.replacements != null && !this.replacements.isEmpty()) {
                    for (Map.Entry<String, String> rp : this.replacements.entrySet()) {
                        value = value.replace(rp.getKey(), rp.getValue());
                    }
                }

                if (!CommonUtils.isNullOrEmpty(value)) {
                    this.contents.put(key, value);
                }
            }
        } else {
            this.contents = null;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public Map<String, String> getContents() {
        return contents;
    }

    public Map<String, String> getTitles() {
        return titles;
    }

    public static class Builder {
        private final Map<String, String> titles;
        private final Map<String, String> replacements;
        private final Map<String, String> msgTemplates;

        public MessageContent build() {
            return new MessageContent(this);
        }

        public Builder addReplacement(String key, String value) {
            this.replacements.put(key, value);
            return this;
        }

        public Builder addTemplate(String language, String template) {
            this.msgTemplates.put(language, template);
            return this;
        }

        public Builder addTitle(Language language, String title) {
            this.titles.put(language.key(), title);
            return this;
        }

        public Builder addTitles(Map<String, String> titles) {
            if (titles != null && !titles.isEmpty()) {
                this.titles.putAll(titles);
            }
            return this;
        }

        private Builder() {
            this.titles = new HashMap();
            this.replacements = new HashMap();
            this.msgTemplates = new HashMap();
        }

    }
}
