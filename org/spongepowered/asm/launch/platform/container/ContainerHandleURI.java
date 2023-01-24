/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.asm.launch.platform.container;

import java.io.File;
import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import org.spongepowered.asm.launch.platform.MainAttributes;
import org.spongepowered.asm.launch.platform.container.IContainerHandle;

public class ContainerHandleURI
implements IContainerHandle {
    private final URI uri;
    private final File file;
    private final MainAttributes attributes;

    public ContainerHandleURI(URI uri) {
        this.uri = uri;
        this.file = this.uri != null ? new File(this.uri) : null;
        this.attributes = MainAttributes.of(uri);
    }

    public URI getURI() {
        return this.uri;
    }

    public File getFile() {
        return this.file;
    }

    @Override
    public String getAttribute(String name) {
        return this.attributes.get(name);
    }

    @Override
    public Collection<IContainerHandle> getNestedContainers() {
        return Collections.emptyList();
    }

    public boolean equals(Object other) {
        if (!(other instanceof ContainerHandleURI)) {
            return false;
        }
        return this.uri.equals(((ContainerHandleURI)other).uri);
    }

    public int hashCode() {
        return this.uri.hashCode();
    }

    public String toString() {
        return String.format("ContainerHandleURI(%s)", this.uri);
    }
}

