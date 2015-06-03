package com.musichub

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import javax.servlet.ServletContext
 
import grails.core.GrailsApplication
import grails.plugins.GrailsPluginManager
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
 
@Singleton
class ApplicationContextHolder implements ApplicationContextAware {

   private ApplicationContext ctx
 
   private static final Map <String, Object> TEST_BEANS = [:]
 
   void setApplicationContext(ApplicationContext applicationContext) {
	   ctx = applicationContext
   }
 
   static ApplicationContext getApplicationContext() {
	  getInstance().ctx
   }
 
   static Object getBean(String name) {
	  TEST_BEANS[name] ?: getApplicationContext().getBean(name)
   }
 
   static GrailsApplication getGrailsApplication() {
	  getBean('grailsApplication')
   }
 
   static ConfigObject getConfig() {
	  getGrailsApplication().config
   }
 
   static ServletContext getServletContext() {
	  getBean('servletContext')
   }
 
   static GrailsPluginManager getPluginManager() {
	  getBean('pluginManager')
   }
 
   // For testing
   static void registerTestBean(String name, bean) {
	  TEST_BEANS[name] = bean
   }
 
   // For testing
   static void unregisterTestBeans() {
	  TEST_BEANS.clear()
   }
}