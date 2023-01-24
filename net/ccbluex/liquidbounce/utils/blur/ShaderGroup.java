/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Charsets
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  com.google.gson.JsonSyntaxException
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.client.shader.Framebuffer
 *  net.minecraft.client.shader.Shader
 *  net.minecraft.client.shader.ShaderUniform
 *  net.minecraft.client.util.JsonException
 *  net.minecraft.util.JsonUtils
 *  net.minecraft.util.ResourceLocation
 *  org.apache.commons.io.IOUtils
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.util.vector.Matrix4f
 */
package net.ccbluex.liquidbounce.utils.blur;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.Shader;
import net.minecraft.client.shader.ShaderUniform;
import net.minecraft.client.util.JsonException;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

public class ShaderGroup {
    private final List<Shader> listShaders = Lists.newArrayList();
    private final Map<String, Framebuffer> mapFramebuffers = Maps.newHashMap();
    private final List<Framebuffer> listFramebuffers = Lists.newArrayList();
    private final Framebuffer mainFramebuffer;
    private final IResourceManager resourceManager;
    private final String shaderGroupName;
    private Matrix4f projectionMatrix;
    private int mainFramebufferWidth;
    private int mainFramebufferHeight;
    private float field_148036_j;
    private float field_148037_k;

    public ShaderGroup(TextureManager p_i1050_1_, IResourceManager p_i1050_2_, Framebuffer p_i1050_3_, ResourceLocation p_i1050_4_) throws IOException, JsonSyntaxException {
        this.resourceManager = p_i1050_2_;
        this.mainFramebuffer = p_i1050_3_;
        this.field_148036_j = 0.0f;
        this.field_148037_k = 0.0f;
        this.mainFramebufferWidth = p_i1050_3_.field_147621_c;
        this.mainFramebufferHeight = p_i1050_3_.field_147618_d;
        this.shaderGroupName = p_i1050_4_.toString();
        this.resetProjectionMatrix();
        this.parseGroup(p_i1050_1_, p_i1050_4_);
    }

    public void parseGroup(TextureManager p_152765_1_, ResourceLocation p_152765_2_) throws IOException, JsonSyntaxException {
        InputStream inputstream;
        block11: {
            JsonParser jsonparser = new JsonParser();
            inputstream = null;
            try {
                IResource iresource = this.resourceManager.func_110536_a(p_152765_2_);
                inputstream = iresource.func_110527_b();
                JsonObject jsonobject = jsonparser.parse(IOUtils.toString((InputStream)inputstream, (Charset)Charsets.UTF_8)).getAsJsonObject();
                if (JsonUtils.func_151202_d((JsonObject)jsonobject, (String)"targets")) {
                    JsonArray jsonarray = jsonobject.getAsJsonArray("targets");
                    int i = 0;
                    for (JsonElement jsonelement : jsonarray) {
                        try {
                            this.initTarget(jsonelement);
                        }
                        catch (Exception exception1) {
                            JsonException jsonexception1 = JsonException.func_151379_a((Exception)exception1);
                            jsonexception1.func_151380_a("targets[" + i + "]");
                            throw jsonexception1;
                        }
                        ++i;
                    }
                }
                if (!JsonUtils.func_151202_d((JsonObject)jsonobject, (String)"passes")) break block11;
                JsonArray jsonarray1 = jsonobject.getAsJsonArray("passes");
                int j = 0;
                for (JsonElement jsonelement1 : jsonarray1) {
                    try {
                        this.parsePass(p_152765_1_, jsonelement1);
                    }
                    catch (Exception exception) {
                        JsonException jsonexception2 = JsonException.func_151379_a((Exception)exception);
                        jsonexception2.func_151380_a("passes[" + j + "]");
                        throw jsonexception2;
                    }
                    ++j;
                }
            }
            catch (Exception exception2) {
                try {
                    JsonException jsonexception = JsonException.func_151379_a((Exception)exception2);
                    jsonexception.func_151381_b(p_152765_2_.func_110623_a());
                    throw jsonexception;
                }
                catch (Throwable throwable) {
                    IOUtils.closeQuietly(inputstream);
                    throw throwable;
                }
            }
        }
        IOUtils.closeQuietly((InputStream)inputstream);
    }

    private void initTarget(JsonElement p_148027_1_) throws JsonException {
        if (JsonUtils.func_151211_a((JsonElement)p_148027_1_)) {
            this.addFramebuffer(p_148027_1_.getAsString(), this.mainFramebufferWidth, this.mainFramebufferHeight);
        } else {
            JsonObject jsonobject = JsonUtils.func_151210_l((JsonElement)p_148027_1_, (String)"target");
            String s = JsonUtils.func_151200_h((JsonObject)jsonobject, (String)"name");
            int i = JsonUtils.func_151208_a((JsonObject)jsonobject, (String)"width", (int)this.mainFramebufferWidth);
            int j = JsonUtils.func_151208_a((JsonObject)jsonobject, (String)"height", (int)this.mainFramebufferHeight);
            if (this.mapFramebuffers.containsKey(s)) {
                throw new JsonException(s + " is already defined");
            }
            this.addFramebuffer(s, i, j);
        }
    }

    private void parsePass(TextureManager p_152764_1_, JsonElement p_152764_2_) throws IOException {
        JsonArray jsonarray1;
        JsonObject jsonobject = JsonUtils.func_151210_l((JsonElement)p_152764_2_, (String)"pass");
        String s = JsonUtils.func_151200_h((JsonObject)jsonobject, (String)"name");
        String s1 = JsonUtils.func_151200_h((JsonObject)jsonobject, (String)"intarget");
        String s2 = JsonUtils.func_151200_h((JsonObject)jsonobject, (String)"outtarget");
        Framebuffer framebuffer = this.getFramebuffer(s1);
        Framebuffer framebuffer1 = this.getFramebuffer(s2);
        if (framebuffer == null) {
            throw new JsonException("Input target '" + s1 + "' does not exist");
        }
        if (framebuffer1 == null) {
            throw new JsonException("Output target '" + s2 + "' does not exist");
        }
        Shader shader = this.addShader(s, framebuffer, framebuffer1);
        JsonArray jsonarray = JsonUtils.func_151213_a((JsonObject)jsonobject, (String)"auxtargets", null);
        if (jsonarray != null) {
            int i = 0;
            for (JsonElement jsonelement : jsonarray) {
                block15: {
                    try {
                        JsonObject jsonobject1 = JsonUtils.func_151210_l((JsonElement)jsonelement, (String)"auxtarget");
                        String s4 = JsonUtils.func_151200_h((JsonObject)jsonobject1, (String)"name");
                        String s3 = JsonUtils.func_151200_h((JsonObject)jsonobject1, (String)"id");
                        Framebuffer framebuffer2 = this.getFramebuffer(s3);
                        if (framebuffer2 == null) {
                            ResourceLocation resourcelocation = new ResourceLocation("textures/effect/" + s3 + "Scare.png");
                            try {
                                this.resourceManager.func_110536_a(resourcelocation);
                            }
                            catch (FileNotFoundException var24) {
                                throw new JsonException("Render target or texture '" + s3 + "' does not exist");
                            }
                            p_152764_1_.func_110577_a(resourcelocation);
                            ITextureObject itextureobject = p_152764_1_.func_110581_b(resourcelocation);
                            int j = JsonUtils.func_151203_m((JsonObject)jsonobject1, (String)"width");
                            int k = JsonUtils.func_151203_m((JsonObject)jsonobject1, (String)"height");
                            boolean flag = JsonUtils.func_151212_i((JsonObject)jsonobject1, (String)"bilinear");
                            if (flag) {
                                GL11.glTexParameteri((int)3553, (int)10241, (int)9729);
                                GL11.glTexParameteri((int)3553, (int)10240, (int)9729);
                            } else {
                                GL11.glTexParameteri((int)3553, (int)10241, (int)9728);
                                GL11.glTexParameteri((int)3553, (int)10240, (int)9728);
                            }
                            shader.func_148041_a(s4, (Object)itextureobject.func_110552_b(), j, k);
                            break block15;
                        }
                        shader.func_148041_a(s4, (Object)framebuffer2, framebuffer2.field_147622_a, framebuffer2.field_147620_b);
                    }
                    catch (Exception exception1) {
                        JsonException jsonexception = JsonException.func_151379_a((Exception)exception1);
                        jsonexception.func_151381_b("auxtargets[" + i + "]");
                        throw jsonexception;
                    }
                }
                ++i;
            }
        }
        if ((jsonarray1 = JsonUtils.func_151213_a((JsonObject)jsonobject, (String)"uniforms", null)) != null) {
            int l = 0;
            for (JsonElement jsonelement1 : jsonarray1) {
                try {
                    this.initUniform(jsonelement1);
                }
                catch (Exception exception) {
                    JsonException jsonexception1 = JsonException.func_151379_a((Exception)exception);
                    jsonexception1.func_151380_a("uniforms[" + l + "]");
                    throw jsonexception1;
                }
                ++l;
            }
        }
    }

    private void initUniform(JsonElement p_148028_1_) throws JsonException {
        JsonObject jsonobject = JsonUtils.func_151210_l((JsonElement)p_148028_1_, (String)"uniform");
        String s = JsonUtils.func_151200_h((JsonObject)jsonobject, (String)"name");
        ShaderUniform shaderuniform = this.listShaders.get(this.listShaders.size() - 1).func_148043_c().func_147991_a(s);
        if (shaderuniform == null) {
            throw new JsonException("Uniform '" + s + "' does not exist");
        }
        float[] afloat = new float[4];
        int i = 0;
        for (JsonElement jsonelement : JsonUtils.func_151214_t((JsonObject)jsonobject, (String)"values")) {
            try {
                afloat[i] = JsonUtils.func_151220_d((JsonElement)jsonelement, (String)"value");
            }
            catch (Exception exception) {
                JsonException jsonexception = JsonException.func_151379_a((Exception)exception);
                jsonexception.func_151380_a("values[" + i + "]");
                throw jsonexception;
            }
            ++i;
        }
        switch (i) {
            default: {
                break;
            }
            case 1: {
                shaderuniform.func_148090_a(afloat[0]);
                break;
            }
            case 2: {
                shaderuniform.func_148087_a(afloat[0], afloat[1]);
                break;
            }
            case 3: {
                shaderuniform.func_148095_a(afloat[0], afloat[1], afloat[2]);
                break;
            }
            case 4: {
                shaderuniform.func_148081_a(afloat[0], afloat[1], afloat[2], afloat[3]);
            }
        }
    }

    public Framebuffer getFramebufferRaw(String p_177066_1_) {
        return this.mapFramebuffers.get(p_177066_1_);
    }

    public void addFramebuffer(String p_148020_1_, int p_148020_2_, int p_148020_3_) {
        Framebuffer framebuffer = new Framebuffer(p_148020_2_, p_148020_3_, true);
        framebuffer.func_147604_a(0.0f, 0.0f, 0.0f, 0.0f);
        this.mapFramebuffers.put(p_148020_1_, framebuffer);
        if (p_148020_2_ == this.mainFramebufferWidth && p_148020_3_ == this.mainFramebufferHeight) {
            this.listFramebuffers.add(framebuffer);
        }
    }

    public void deleteShaderGroup() {
        for (Framebuffer framebuffer : this.mapFramebuffers.values()) {
            framebuffer.func_147608_a();
        }
        for (Shader shader : this.listShaders) {
            shader.func_148044_b();
        }
        this.listShaders.clear();
    }

    public Shader addShader(String p_148023_1_, Framebuffer p_148023_2_, Framebuffer p_148023_3_) throws IOException {
        Shader shader = new Shader(this.resourceManager, p_148023_1_, p_148023_2_, p_148023_3_);
        this.listShaders.add(this.listShaders.size(), shader);
        return shader;
    }

    private void resetProjectionMatrix() {
        this.projectionMatrix = new Matrix4f();
        this.projectionMatrix.setIdentity();
        this.projectionMatrix.m00 = 2.0f / (float)this.mainFramebuffer.field_147622_a;
        this.projectionMatrix.m11 = 2.0f / (float)(-this.mainFramebuffer.field_147620_b);
        this.projectionMatrix.m22 = -0.0020001999f;
        this.projectionMatrix.m33 = 1.0f;
        this.projectionMatrix.m03 = -1.0f;
        this.projectionMatrix.m13 = 1.0f;
        this.projectionMatrix.m23 = -1.0001999f;
    }

    public void createBindFramebuffers(int width, int height) {
        this.mainFramebufferWidth = this.mainFramebuffer.field_147622_a;
        this.mainFramebufferHeight = this.mainFramebuffer.field_147620_b;
        this.resetProjectionMatrix();
        for (Shader shader : this.listShaders) {
            shader.func_148045_a(this.projectionMatrix);
        }
        for (Framebuffer framebuffer : this.listFramebuffers) {
            framebuffer.func_147613_a(width, height);
        }
    }

    public void loadShaderGroup(float partialTicks) {
        if (partialTicks < this.field_148037_k) {
            this.field_148036_j += 1.0f - this.field_148037_k;
            this.field_148036_j += partialTicks;
        } else {
            this.field_148036_j += partialTicks - this.field_148037_k;
        }
        this.field_148037_k = partialTicks;
        while (this.field_148036_j > 20.0f) {
            this.field_148036_j -= 20.0f;
        }
        for (Shader shader : this.listShaders) {
            shader.func_148042_a(this.field_148036_j / 20.0f);
        }
    }

    public final String getShaderGroupName() {
        return this.shaderGroupName;
    }

    private Framebuffer getFramebuffer(String p_148017_1_) {
        return p_148017_1_ == null ? null : (p_148017_1_.equals("minecraft:main") ? this.mainFramebuffer : this.mapFramebuffers.get(p_148017_1_));
    }

    public List<Shader> getShaders() {
        return this.listShaders;
    }
}

