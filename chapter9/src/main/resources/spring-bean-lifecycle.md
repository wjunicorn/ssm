```flow
st=>start: 开始
end=>end: 结束

get-bean=>operation: 调用getBean(...)方法获取某个Bean
st->get-bean

before-instantiation=>operation: 调用InstantiationAwareBeanPostProcessor的postProcessBeforeInstantiation(...)方法
instantiation=>operation: 实例化Bean
after-instantiation=>operation: 调用InstantiationAwareBeanPostProcessor的postProcessAfterInstantiation(...)方法
property-values-instantiation=>operation: 调用InstantiationAwareBeanPostProcessor的postProcessPropertyValues(...)方法
set-property-values=>operation: 设置属性值

get-bean->before-instantiation->instantiation->after-instantiation->property-values-instantiation->set-property-values

set-bean-name=>operation: 调用BeanNameAware的setBeanName(...)方法
set-bean-factory=>operation: 调用BeanFactoryAware的setBeanFactory(...)方法
set-application-context=>operation: 调用ApplicationContextAware的setApplicationContext(...)方法

set-property-values->set-bean-name->set-bean-factory->set-application-context

before-initialization=>operation: 调用BeanPostProcessor的postProcessBeforeInitialization(...)方法
after-properties-set=>operation: 调用InitializingBean的afterPropertiesSet(...)方法
initialization=>operation: 调用init-method属性配置的初始化方法
after-initialization=>operation: 调用BeanPostProcessor的postProcessAfterInitialization(...)方法

set-application-context->before-initialization->after-properties-set->initialization->after-initialization

cond-bean-scope=>condition: Bean的scope是否为singleton？
singleton-bean=>inputoutput: 在Spring缓存池中准备就绪的Bean
prototype-bean=>inputoutput: 将准备就绪的Bean交给调用者
destroy-container=>operation: 容器销毁
user-defined-destroy-bean=>operation: 调用destroy-method属性配置的销毁方法
destroy-bean=>operation: 调用DisposableBean的destroy(...)方法

after-initialization->cond-bean-scope
cond-bean-scope(yes)->singleton-bean->destroy-container->user-defined-destroy-bean->destroy-bean->end
cond-bean-scope(no)->prototype-bean
```