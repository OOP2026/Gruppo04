package project.arch;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "")
public class LayerContainmentTest {
    @ArchTest
    public static final ArchRule controllers_reside_in_the_controller_layer = classes()
            .that().haveSimpleNameEndingWith("Controller")
            .should().resideInAPackage("controller");

    @ArchTest
    public static final ArchRule controllers_only_reside_in_the_controller_layer = noClasses()
            .that().haveSimpleNameNotEndingWith("Controller")
            .should().resideInAPackage("controller");

    @ArchTest
    public static final ArchRule DAOs_reside_in_the_DAO_layer = classes()
            .that().haveSimpleNameEndingWith("DAO")
            .should().resideInAPackage("dao");
            .should().resideInAPackage("project.dao");

    @ArchTest
    public static final ArchRule DAOs_only_reside_in_the_DAO_layer = noClasses()
            .should().resideInAPackage("dao");
            .should().resideInAPackage("project.dao");

    @ArchTest
    public static final ArchRule DAO_implementations_reside_in_the_DAO_implementation_layer = classes()
            .should().resideInAPackage("implementazioneDao");
            .should().resideInAPackage("project.implementazioneDao");

    @ArchTest
    public static final ArchRule DAO_implementations_only_reside_in_the_DAO_implementation_layer = noClasses()
            .should().resideInAPackage("implementazioneDao");
            .should().resideInAPackage("project.implementazioneDao");

    @ArchTest
    public static final ArchRule GUIs_reside_in_the_GUI_layer = classes()
            .that().haveSimpleNameEndingWith("Frame")
            .should().resideInAPackage("gui");

    @ArchTest
    public static final ArchRule GUIs_only_reside_in_the_GUI_layer = noClasses()
            .that().haveSimpleNameNotEndingWith("Frame")
            .should().resideInAPackage("gui");
}
