package com.kcanmin.aop.ex05;

import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// import lombok.AllArgsConstructor;

// @AllArgsConstructor
@Configuration
public class Config {
    @Autowired
    private Seasoning seasoning;

    // @Autowired
    // private ProxyFactory factory;

    // first 메서드는 ProxytFactory를 

    @Autowired
    @Qualifier("target")
    private First first;

    // autoWired 귀찮으면 그냥 위에다가 Allargsconstructer 써주기. 보통 configure 클래스에는 allargs보단 그냥 autowired 로 추가해줄 것.

    @Bean
    public ProxyFactory proxyFactory(){
        return new ProxyFactory();
    }

    @Bean
    public Pointcut pointcut(){
        AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
        // pc.setExpression("execution(* two(..))");
        pc.setExpression("execution(* two(..))");
        return pc;
    }

    @Bean
    public Advisor advisor(){
        return new DefaultPointcutAdvisor(pointcut() , seasoning);
    }

    @Bean("proxy")
    public First first(){
        // 얘가 프록시가 될 것임니다.....
        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(first);
        factory.addAdvisor(advisor());
        return (First) factory.getProxy();
    }

}

// 내가 만든 거 아니면 무조건 Bean 붙이기
// configuration 어노테이션 활용할 것.


// 코드에서 advice가 두 번 동작하는 이유는 proxy 빈이 이미 AOP 프록시로 감싸져 있는데, Spring에서 빈 생성을 처리하는 과정에서 추가적으로 프록시를 감싸는 경우가 발생할 수 있기 때문입니다. 이 문제는 Spring AOP의 프록시 중첩(proxy chaining) 문제와 관련이 있습니다.

// 문제의 핵심
// proxy 빈의 생성 과정:

// first() 메서드는 ProxyFactory를 사용해 first 객체를 AOP 프록시로 감싸고 이를 반환합니다.
// 이 과정에서 이미 advisor가 추가되어 Seasoning의 advice가 적용된 상태입니다.
// Spring의 자동 프록싱:

// Spring은 @Configuration 클래스에서 정의된 @Bean 메서드 호출을 프록시로 감싸는 경우가 있습니다.
// Spring의 AOP 인프라는 특정 조건에서 @Bean 메서드를 호출한 결과물(여기서는 proxy 빈)을 추가로 감싸려고 시도합니다.
// 이로 인해 기존에 프록시로 감싸져 있던 객체가 또 한 번 감싸지는 상황이 발생할 수 있습니다.
// 중첩된 프록시의 동작:

// 첫 번째 프록시와 두 번째 프록시 모두 동일한 advisor를 가지고 있기 때문에, 동일한 advice가 두 번 호출됩니다.
// 해결 방법
// 이 문제를 방지하기 위해 다음 방법 중 하나를 사용할 수 있습니다.

// 1. 프록시 중복 생성 방지
// Spring AOP의 자동 프록시 생성 기능을 우회하여, 직접 생성한 프록시 객체를 Spring이 추가로 감싸지 않도록 설정합니다.

// proxy 빈을 생성할 때 @Primary를 사용하거나, Spring의 @Scope와 함께 proxyMode를 설정해 중복 프록시 생성을 방지합니다.
// java
// 코드 복사
// @Bean("proxy")
// @Scope(proxyMode = ScopedProxyMode.NO)
// public First first() {
//     ProxyFactory factory = proxyFactory();
//     factory.setTarget(first);
//     factory.addAdvisor(advisor());
//     return (First) factory.getProxy();
// }
// 2. @Bean 메서드에서 직접 생성하지 않기
// proxy 빈을 ProxyFactory가 아닌 AOP 설정 방식으로 등록합니다. Spring AOP 설정을 통해 Advisor와 Pointcut을 지정하고, 자동으로 프록시를 생성하도록 합니다.

// java
// 코드 복사
// @Bean
// public First first() {
//     return first; // 원래 객체 반환
// }

// @Bean
// public ProxyFactoryBean proxyFactoryBean() {
//     ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
//     proxyFactoryBean.setTarget(first());
//     proxyFactoryBean.addAdvisor(advisor());
//     return proxyFactoryBean;
// }
// 3. @Aspect를 사용한 설정으로 전환
// Spring AOP의 일반적인 사용 방식인 @Aspect를 사용하면, 이러한 중복 프록시 문제를 예방할 수 있습니다.

// java
// 코드 복사
// @Aspect
// @Component
// public class SeasoningAspect {
//     @Pointcut("execution(* one(..))")
//     public void pointcut() {}

//     @Before("pointcut()")
//     public void beforeAdvice() {
//         // Advice 내용
//     }
// }
// // @Aspect를 사용하면 Spring이 자동으로 proxy 생성 및 관리 과정을 처리하므로, 중복 프록시 문제를 방지할 수 있습니다.

// // 요약
// // advice가 두 번 동작하는 이유는 Spring의 빈 생성 과정에서 이미 프록시로 감싸진 객체를 또 다른 프록시로 감싸는 중첩 프록시 현상 때문입니다. 이를 해결하려면 프록시 중복 생성을 방지하거나, Spring AOP의 표준 설정 방식을 사용해 프록시 생성 과정을 단순화해야 합니다.