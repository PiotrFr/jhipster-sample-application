package pl.citysoft;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("pl.citysoft");

        noClasses()
            .that()
            .resideInAnyPackage("pl.citysoft.service..")
            .or()
            .resideInAnyPackage("pl.citysoft.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..pl.citysoft.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
