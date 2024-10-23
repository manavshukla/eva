package com.example.security.advice;

import com.example.security.utils.AuthUtil;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

@Component
@AllArgsConstructor
public class TenantIdentifierResolver
    implements CurrentTenantIdentifierResolver, HibernatePropertiesCustomizer {

  private static final String DEFAULT_TENANT = "DEFAULT";
  private final AuthUtil authService;

  @Override
  public String resolveCurrentTenantIdentifier() {
    if (RequestContextHolder.getRequestAttributes() == null
            || SecurityContextHolder.getContext().getAuthentication() == null
            || SecurityContextHolder.getContext()
                   .getAuthentication()
                   .getPrincipal()
                   .equals("anonymousUser")) {
      return DEFAULT_TENANT;
    }
    return authService.getLoggedInShopId();
  }

  @Override
  public boolean validateExistingCurrentSessions() {
    return false;
  }

  @Override
  public boolean isRoot(String tenantId) {
    return DEFAULT_TENANT.equals(tenantId) || isRootUser();
  }

  private boolean isRootUser() {
    return false;
  }

  @Override
  public void customize(Map<String, Object> hibernateProperties) {
    hibernateProperties.put(AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER, this);
  }
}
