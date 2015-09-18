package com.cafeteira;

import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;

@AllArgsConstructor
public abstract class CafeDecorator extends Cafe {

	@Delegate
	protected Cafe cafe;

}
