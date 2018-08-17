package io.skalogs.skaetl.rules.metrics.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.skalogs.skaetl.domain.ESBuffer;
import io.skalogs.skaetl.domain.IndexShape;
import io.skalogs.skaetl.domain.RetentionLevel;
import io.skalogs.skaetl.rules.metrics.domain.Keys;
import io.skalogs.skaetl.rules.metrics.domain.MetricResult;
import io.skalogs.skaetl.service.ESErrorRetryWriter;
import io.skalogs.skaetl.service.processor.AbstractElasticsearchProcessor;
import io.skalogs.skaetl.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MetricsElasticsearchProcessor extends AbstractElasticsearchProcessor<Keys, MetricResult> {

    private final RetentionLevel retentionLevel;
    private final IndexShape indexShape;

    public MetricsElasticsearchProcessor(ESBuffer esBuffer, ESErrorRetryWriter esErrorRetryWriter, RetentionLevel retention, IndexShape indexShape) {
        super(esBuffer, esErrorRetryWriter);
        retentionLevel = retention;
        this.indexShape = indexShape;
    }

    @Override
    public void process(Keys key, MetricResult value) {
        try {
            String valueAsString = JSONUtils.getInstance().asJsonString(value);
            String metricId = value.getRuleName() + value.getTimestamp() + value.getKeys().hashCode();
            if (value.getElement() != null) {
                metricId += value.getElement().toString();
            }
            processToElasticsearch(value.getTimestamp(), value.getProject(), "metrics", retentionLevel, indexShape, valueAsString, metricId);
        } catch (JsonProcessingException e) {
            log.error("Couldn't transform value as metric " + key, e);
        }
    }
}
