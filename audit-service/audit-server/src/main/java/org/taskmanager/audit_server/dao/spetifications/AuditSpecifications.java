package org.taskmanager.audit_server.dao.spetifications;

import org.springframework.data.jpa.domain.Specification;
import org.taskmanager.audit_server.dao.entity.Audit;

import java.time.LocalDateTime;
import java.util.UUID;

public class AuditSpecifications {
    public static Specification<Audit> byCreateDateFrom(LocalDateTime from) {
        return (root, query, builder) ->
                builder.greaterThanOrEqualTo(root.get("createDate"), from);
    }
    public static Specification<Audit> byCreateDateTo(LocalDateTime to) {
        return (root, query, builder) ->
                builder.lessThanOrEqualTo(root.get("createDate"), to);
    }
    public static Specification<Audit> byUser(UUID user) {
        return (root, query, builder) ->
                builder.equal(root.get("userId"), user);
    }
}
