package activities.lambdas3watermark;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class FileProcessorHandler implements RequestHandler<S3Event, Void> {

    @Override
    public Void handleRequest(S3Event input, Context context) {
        input.getRecords().forEach(r -> {
            String bucket = input.getRecords().getFirst().getS3().getBucket().getName();
            String key = input.getRecords().getFirst().getS3().getObject().getKey();
            context.getLogger().log(String.format("Processing file %s in bucket %s", key, bucket));
        });
        return null;
    }
}
