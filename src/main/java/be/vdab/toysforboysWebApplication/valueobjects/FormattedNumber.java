package be.vdab.toysforboysWebApplication.valueobjects;

import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;

public class FormattedNumber {

	@NumberFormat(pattern = "#,##0.00")
	private final BigDecimal value;

	public FormattedNumber(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getValue() {
		return value;
	}
}