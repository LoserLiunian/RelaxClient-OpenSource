/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.asm.launch.platform.container;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import org.spongepowered.asm.launch.platform.container.ContainerHandleURI;
import org.spongepowered.asm.launch.platform.container.ContainerHandleVirtual;

public class ContainerHandleModLauncher
extends ContainerHandleVirtual {
    public ContainerHandleModLauncher(String name) {
        super(name);
    }

    public void addResource(String name, Path path) {
        this.add(new Resource(name, path));
    }

    public void addResources(List<Map.Entry<String, Path>> resources) {
        for (Map.Entry<String, Path> resource : resources) {
            this.addResource(resource.getKey(), resource.getValue());
        }
    }

    @Override
    public String toString() {
        return String.format("ModLauncher Root Container(%x)", this.hashCode());
    }

    public class Resource
    extends ContainerHandleURI {
        private String name;
        private Path path;

        public Resource(String name, Path path) {
            super(path.toUri());
            this.name = name;
            this.path = path;
        }

        public String getName() {
            return this.name;
        }

        public Path getPath() {
            return this.path;
        }

        @Override
        public String toString() {
            return String.format("ContainerHandleModLauncher.Resource(%s:%s)", this.name, this.path);
        }
    }
}

