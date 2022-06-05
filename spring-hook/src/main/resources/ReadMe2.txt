1. Aware族
2. PostProcessor
3. Import族
4. Initialize和Disposer
5. FactoryBean
6. ApplicationListener


1. Aware族
    ApplicationContextAware
    BeanFactoryAware
    BeanNameAware
    BeanClassLoaderAware
    EnvironmentAware
2. PostProcessor
    BeanFactoryPostProcessor
    BeanPostProcessor
3. Import族
    ImportBeanDefinitionRegistrar
    ImportSelector
4. Initialize和Disposer
    BeanDefinitionPostProcessorHook
    InitializingBean, DisposableBean
5. FactoryBean
   实现FactoryBean
6. ApplicationListener
    监听器的实现