package com.github.gresh113.bionimine.util;

/*Test Mod 3 - Copyright (c) 2015-2017 Choonster

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.*/

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.util.LazyValue;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

/**
 * Implementation of {@link IForgeRegistry} that delegates to an {@link IForgeRegistry} instance that's lazily loaded
 * from {@link GameRegistry}.
 *
 * @author Choonster
 */
public class LazyForgeRegistry<V extends IForgeRegistryEntry<V>> implements IForgeRegistry<V> {
	private final LazyValue<IForgeRegistry<V>> registry;

	protected LazyForgeRegistry(final Class<V> registryType) {
		registry = new LazyValue<>(() ->
				Objects.requireNonNull(
						GameRegistry.findRegistry(registryType),
						() -> String.format("Registry of type %s not present", registryType.getName())
				)
		);
	}

	public static <V extends IForgeRegistryEntry<V>> LazyForgeRegistry<V> of(final Class<V> registryType) {
		return new LazyForgeRegistry<>(registryType);
	}

	private IForgeRegistry<V> getRegistry() {
		return registry.getValue();
	}

	@Override
	public ResourceLocation getRegistryName() {
		return getRegistry().getRegistryName();
	}

	@Override
	public Class<V> getRegistrySuperType() {
		return getRegistry().getRegistrySuperType();
	}

	@Override
	public void register(final V value) {
		getRegistry().register(value);
	}

	@SafeVarargs
	@Override
	public final void registerAll(final V... values) {
		getRegistry().registerAll(values);
	}

	@Override
	public boolean containsKey(final ResourceLocation key) {
		return getRegistry().containsKey(key);
	}

	@Override
	public boolean containsValue(final V value) {
		return getRegistry().containsValue(value);
	}

	@Override
	public boolean isEmpty() {
		return getRegistry().isEmpty();
	}

	@Override
	@Nullable
	public V getValue(final ResourceLocation key) {
		return getRegistry().getValue(key);
	}

	@Override
	@Nullable
	public ResourceLocation getKey(final V value) {
		return getRegistry().getKey(value);
	}

	@Override
	@Nullable
	public ResourceLocation getDefaultKey() {
		return getRegistry().getDefaultKey();
	}

	@Override
	@Nonnull
	public Set<ResourceLocation> getKeys() {
		return getRegistry().getKeys();
	}

	@Override
	@Nonnull
	public Collection<V> getValues() {
		return getRegistry().getValues();
	}

	@Override
	@Nonnull
	public Set<Map.Entry<ResourceLocation, V>> getEntries() {
		return getRegistry().getEntries();
	}

	@Override
	public <T> T getSlaveMap(final ResourceLocation slaveMapName, final Class<T> type) {
		return getRegistry().getSlaveMap(slaveMapName, type);
	}

	@Override
	public Iterator<V> iterator() {
		return getRegistry().iterator();
	}
}
