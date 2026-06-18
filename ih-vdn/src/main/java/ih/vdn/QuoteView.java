package ih.vdn;

import java.time.LocalDate;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

/**
 * Quote request form collecting the information usually needed for a vehicle
 * insurance quote: driver details, vehicle details, and coverage preferences.
 */
@PageTitle("InsuranceHub | Get a quote")
@Route("quote")
public class QuoteView extends VerticalLayout {

    public QuoteView() {
        addClassName("hero");
        addClassName("hero--form");
        setWidthFull();
        setPadding(false);
        setSpacing(false);

        var inner = new VerticalLayout(buildForm());
        inner.addClassName("hero__inner");
        inner.setWidthFull();
        add(inner);
    }

    private VerticalLayout buildForm() {
        var eyebrow = new Span("GET A QUOTE");
        eyebrow.addClassName("hero__eyebrow");

        var title = new H1("Tell us about you and your vehicle.");
        title.addClassName("hero__title");

        var subtitle = new Paragraph(
                "Fill in the details below to receive a personalized vehicle insurance quote.");
        subtitle.addClassName("hero__subtitle");

        var driver = buildDriverSection();
        var vehicle = buildVehicleSection();
        var coverage = buildCoverageSection();

        var submit = new Button("Get my quote");
        submit.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_LARGE);
        submit.addClickListener(e -> submit.getUI().ifPresent(ui -> ui.navigate(QuoteResultView.class)));

        var back = new RouterLink("", MainView.class);
        var backButton = new Button("Back to home");
        backButton.addThemeVariants(ButtonVariant.LUMO_LARGE, ButtonVariant.LUMO_TERTIARY);
        back.add(backButton);

        var actions = new HorizontalLayout(submit, back);
        actions.addClassName("hero__actions");

        var text = new VerticalLayout(eyebrow, title, subtitle, driver, vehicle, coverage, actions);
        text.addClassName("hero__text");
        text.setPadding(false);
        text.setSpacing(false);
        return text;
    }

    private VerticalLayout buildDriverSection() {
        var firstName = new TextField("First name");
        var lastName = new TextField("Last name");

        var dob = new DatePicker("Date of birth");
        dob.setMax(LocalDate.now());

        var email = new EmailField("Email");
        var phone = new TextField("Phone");

        var address = new TextField("Street address");
        var city = new TextField("City");
        var state = new TextField("State / Province");
        var zip = new TextField("ZIP / Postal code");

        var maritalStatus = new Select<String>();
        maritalStatus.setLabel("Marital status");
        maritalStatus.setItems("Single", "Married", "Divorced", "Widowed");

        var licenseAge = new IntegerField("Age when first licensed");
        licenseAge.setMin(14);
        licenseAge.setMax(100);
        licenseAge.setStepButtonsVisible(true);

        return section("Driver details",
                firstName, lastName, dob, email, phone,
                address, city, state, zip, maritalStatus, licenseAge);
    }

    private VerticalLayout buildVehicleSection() {
        var year = new IntegerField("Year");
        year.setMin(1900);
        year.setMax(LocalDate.now().getYear() + 1);
        year.setStepButtonsVisible(true);

        var make = new TextField("Make");
        var model = new TextField("Model");
        var vin = new TextField("VIN");

        var usage = new Select<String>();
        usage.setLabel("Primary use");
        usage.setItems("Commute", "Pleasure", "Business", "Rideshare");

        var annualMileage = new IntegerField("Estimated annual mileage");
        annualMileage.setMin(0);
        annualMileage.setStep(1000);
        annualMileage.setStepButtonsVisible(true);

        var ownership = new Select<String>();
        ownership.setLabel("Ownership");
        ownership.setItems("Owned", "Financed", "Leased");

        var parking = new Select<String>();
        parking.setLabel("Where is it parked overnight?");
        parking.setItems("Garage", "Driveway", "Street", "Parking lot");

        return section("Vehicle details",
                year, make, model, vin, usage, annualMileage, ownership, parking);
    }

    private VerticalLayout buildCoverageSection() {
        var coverageLevel = new Select<String>();
        coverageLevel.setLabel("Coverage level");
        coverageLevel.setItems("Liability only", "Standard", "Comprehensive", "Full coverage");

        var deductible = new Select<String>();
        deductible.setLabel("Deductible");
        deductible.setItems("$250", "$500", "$1,000", "$2,000");

        var startDate = new DatePicker("Desired start date");
        startDate.setMin(LocalDate.now());

        var priorInsurance = new Checkbox("I currently have auto insurance");
        var accidents = new Checkbox("Accidents or claims in the last 5 years");
        var violations = new Checkbox("Traffic tickets in the last 3 years");

        var extras = new VerticalLayout(priorInsurance, accidents, violations);
        extras.setPadding(false);
        extras.setSpacing(false);

        var form = formLayout();
        form.add(coverageLevel, deductible, startDate);
        form.setColspan(startDate, 1);

        var heading = new H2("Coverage preferences");
        heading.addClassName("hero__section-title");

        var wrapper = new VerticalLayout(heading, form, extras);
        wrapper.setPadding(false);
        wrapper.setSpacing(false);
        wrapper.setWidthFull();
        return wrapper;
    }

    private VerticalLayout section(String heading, com.vaadin.flow.component.Component... fields) {
        var form = formLayout();
        form.add(fields);

        var title = new H2(heading);
        title.addClassName("hero__section-title");

        var wrapper = new VerticalLayout(title, form);
        wrapper.setPadding(false);
        wrapper.setSpacing(false);
        wrapper.setWidthFull();
        return wrapper;
    }

    private FormLayout formLayout() {
        var form = new FormLayout();
        form.setWidthFull();
        form.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("480px", 2),
                new FormLayout.ResponsiveStep("768px", 3));
        return form;
    }
}
