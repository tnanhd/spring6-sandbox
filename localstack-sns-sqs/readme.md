### Commands to create SNS topic and SQS queue, subscribe the queue to the topic, and publish a message to the topic

```bash
# Create SNS topic
awslocal sns create-topic --name my-test-topic
# Get the ARN of the SNS topic
TOPIC_ARN=$(awslocal sns list-topics --query "Topics[?contains(TopicArn, 'my-test-topic')].TopicArn" --output text)

# Create SQS queue 1
awslocal sqs create-queue --queue-name my-test-queue
# Get the URL of the SQS queue
QUEUE_URL=$(awslocal sqs get-queue-url --queue-name my-test-queue --query "QueueUrl" --output text)
# Get the ARN of the SQS queue
QUEUE_ARN=$(awslocal sqs get-queue-attributes --queue-url $QUEUE_URL --attribute-names QueueArn --query "Attributes.QueueArn" --output text)
# Subscribe the SQS queue to the SNS topic
awslocal sns subscribe --topic-arn $TOPIC_ARN --protocol sqs --notification-endpoint $QUEUE_ARN

# Create SQS queue 2 (with filter policy)
awslocal sqs create-queue --queue-name my-filtered-queue
# Get the URL of the SQS queue
QUEUE_WITH_FILTER_URL=$(awslocal sqs get-queue-url --queue-name my-filtered-queue --query "QueueUrl" --output text)
# Get the ARN of the SQS queue
QUEUE_WITH_FILTER_ARN=$(awslocal sqs get-queue-attributes --queue-url $QUEUE_WITH_FILTER_URL --attribute-names QueueArn --query "Attributes.QueueArn" --output text)
# Subscribe the SQS queue to the SNS topic
awslocal sns subscribe --topic-arn $TOPIC_ARN --protocol sqs --notification-endpoint $QUEUE_WITH_FILTER_ARN
# Get the ARN of the subscription
SUBSCRIPTION_ARN=$(awslocal sns list-subscriptions-by-topic --topic-arn $TOPIC_ARN --query "Subscriptions[?Endpoint=='$QUEUE_WITH_FILTER_ARN'].SubscriptionArn" --output text)
# Configure the SNS subscription filter policy with message attributes
awslocal sns set-subscription-attributes \
      --subscription-arn $SUBSCRIPTION_ARN \
      --attribute-name FilterPolicy \
      --attribute-value '{"target-filtered": ["true"]}'
```