package com.ham.connectors.myfirst.internal.extension;


import org.mule.runtime.extension.api.annotation.Configurations;
import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;

import com.ham.connectors.myfirst.internal.configuration.MyFirstConfiguration;

@Xml(prefix = "myfirst")
@Extension(name = "MyFirst")
@Configurations(MyFirstConfiguration.class)
public class MyFirstExtension {

}
