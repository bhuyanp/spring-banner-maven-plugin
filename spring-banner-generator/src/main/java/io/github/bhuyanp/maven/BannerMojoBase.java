package io.github.bhuyanp.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.util.List;

public abstract class BannerMojoBase extends AbstractMojo implements BannerTextUtil {
    public static final String BANNER_TEXT_DEFAULT_VALUE = "${project.name}";


    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    MavenProject project;

    @Parameter(property = "bannerText", defaultValue = BANNER_TEXT_DEFAULT_VALUE)
    String bannerText;

    @Parameter(property = "bannerFonts")
    String[] bannerFonts;

    @Parameter(property = "captionText",  defaultValue = CAPTION_DEFAULT_VALUE)
    String captionText;

    @Parameter(property = "themePreset", defaultValue = "DARK")
    String themePreset;


}
