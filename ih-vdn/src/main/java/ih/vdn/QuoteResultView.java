package ih.vdn;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

/**
 * Mock quote result page: presents an example premium and a brief explanation
 * of how the value was determined. The numbers are illustrative only.
 */
@PageTitle("InsuranceHub | Your quote")
@Route("quote/result")
public class QuoteResultView extends VerticalLayout {

    public QuoteResultView() {
        addClassName("hero");
        addClassName("hero--form");
        setWidthFull();
        setPadding(false);
        setSpacing(false);

        var inner = new VerticalLayout(buildContent());
        inner.addClassName("hero__inner");
        inner.setWidthFull();
        add(inner);
    }

    private VerticalLayout buildContent() {
        var eyebrow = new Span("YOUR QUOTE");
        eyebrow.addClassName("hero__eyebrow");

        var title = new H1("Here's your estimated premium.");
        title.addClassName("hero__title");

        var amount = new Span("$128");
        amount.addClassName("quote-result__amount");
        var period = new Span(" / month");
        period.addClassName("quote-result__period");
        var price = new HorizontalLayout(amount, period);
        price.addClassName("quote-result__price");
        price.setAlignItems(Alignment.BASELINE);

        var annual = new Paragraph("That's about $1,536 per year, billed monthly with no hidden fees.");
        annual.addClassName("hero__subtitle");

        var howTitle = new H2("How we calculated this");
        howTitle.addClassName("hero__section-title");

        var explanation = new Paragraph(
                "This estimate starts from a base rate for your vehicle and coverage level, then adjusts "
                        + "for the details you provided:");
        explanation.addClassName("hero__subtitle");

        var factors = new VerticalLayout(
                factor("Base premium", "Standard coverage for your vehicle make, model, and year.", "$95"),
                factor("Driver profile", "Your age, location, and driving history.", "+ $24"),
                factor("Annual mileage", "Lower mileage keeps your premium down.", "− $6"),
                factor("Deductible", "A higher deductible lowers your monthly cost.", "+ $15"));
        factors.addClassName("quote-result__factors");
        factors.setPadding(false);
        factors.setSpacing(false);

        var disclaimer = new Paragraph(
                "This is a demo application — the quote above is illustrative and not a real insurance offer.");
        disclaimer.addClassName("quote-result__disclaimer");

        var startButton = new Button("Accept and continue");
        startButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_LARGE);
        startButton.addClickListener(e -> startButton.getUI().ifPresent(ui -> ui.navigate(ThankYouView.class)));

        var edit = new RouterLink("", QuoteView.class);
        var editButton = new Button("Edit my details");
        editButton.addThemeVariants(ButtonVariant.LUMO_LARGE, ButtonVariant.LUMO_TERTIARY);
        edit.add(editButton);

        var actions = new HorizontalLayout(startButton, edit);
        actions.addClassName("hero__actions");

        var text = new VerticalLayout(eyebrow, title, price, annual,
                howTitle, explanation, factors, disclaimer, actions);
        text.addClassName("hero__text");
        text.setPadding(false);
        text.setSpacing(false);
        return text;
    }

    private HorizontalLayout factor(String name, String detail, String amount) {
        var label = new Span(name);
        label.addClassName("quote-result__factor-name");
        var description = new Span(detail);
        description.addClassName("quote-result__factor-detail");

        var labels = new VerticalLayout(label, description);
        labels.setPadding(false);
        labels.setSpacing(false);

        var value = new Span(amount);
        value.addClassName("quote-result__factor-value");

        var row = new HorizontalLayout(labels, value);
        row.addClassName("quote-result__factor");
        row.setWidthFull();
        row.setJustifyContentMode(JustifyContentMode.BETWEEN);
        row.setAlignItems(Alignment.CENTER);
        return row;
    }
}
