# order-service

A Spring Boot service for order management.

## Intentional Failure

`OrderService.applyDiscount(Order, Double)` does **not** guard against a `null`
discount percentage.  The test `applyDiscount_withNullDiscount_shouldThrowMeaningfulError`
deliberately passes `null`, causing the CI build to fail with a
`java.lang.NullPointerException` so the diagnosis copilot can detect and explain
the root cause.

## Local build

```bash
mvn clean verify
```
