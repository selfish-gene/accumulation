package com.selfish.gene.annotation.customize_annotation.demo_apt;

import org.apache.commons.io.IOUtils;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Set;

/**
 * Created by Administrator on 2017/6/25.
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class AnnotationProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        PrintStream ps = null;
        for(Element t : roundEnv.getElementsAnnotatedWith(Persistent.class)){
            Name clazzName = t.getSimpleName();
            Persistent per = t.getAnnotation(Persistent.class);
            try {
                String filePath = "D:\\git\\accumulation\\src\\main\\java\\com\\selfish\\gene\\annotation\\customize_annotation\\demo_apt\\Person.xml";
                ps = new PrintStream(filePath);
                // 执行输出
                ps.println("<?xml version=\"1.0\"?>");
                ps.println("<!DOCTYPE hibernate-mapping PUBLIC");
                ps.println("	\"-//Hibernate/Hibernate "
                        + "Mapping DTD 3.0//EN\"");
                ps.println("	\"http://www.hibernate.org/dtd/"
                        + "hibernate-mapping-3.0.dtd\">");
                ps.println("<hibernate-mapping>");
                ps.print("	<class name=\"" + t);
                // 输出per的table()的值
                ps.println("\" table=\"" + per.table() + "\">");
                for (Element f : t.getEnclosedElements())
                {
                    // 只处理成员变量上的Annotation
                    if (f.getKind() == ElementKind.FIELD)   // ①
                    {
                        // 获取成员变量定义前的@Id Annotation
                        Id id = f.getAnnotation(Id.class);      // ②
                        // 当@Id Annotation存在时输出<id.../>元素
                        if(id != null)
                        {
                            ps.println("		<id name=\""
                                    + f.getSimpleName()
                                    + "\" column=\"" + id.column()
                                    + "\" type=\"" + id.type()
                                    + "\">");
                            ps.println("		<generator class=\""
                                    + id.generator() + "\"/>");
                            ps.println("		</id>");
                        }
                        // 获取成员变量定义前的@Property Annotation
                        Property p = f.getAnnotation(Property.class);  // ③
                        // 当@Property Annotation存在时输出<property.../>元素
                        if (p != null)
                        {
                            ps.println("		<property name=\""
                                    + f.getSimpleName()
                                    + "\" column=\"" + p.column()
                                    + "\" type=\"" + p.type()
                                    + "\"/>");
                        }
                    }
                }
                ps.println("	</class>");
                ps.println("</hibernate-mapping>");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            finally {
                IOUtils.closeQuietly(ps);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // TODO javac编译失败   javac -processor AnnotationProcessor com.selfish.gene.annotation.customize_annotation.demo_apt.Person.java
        String filePath = AnnotationProcessor.class.getClassLoader().getResource("\\").getPath();
        System.out.println(filePath);
    }
}
