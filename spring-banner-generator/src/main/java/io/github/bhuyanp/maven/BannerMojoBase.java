package io.github.bhuyanp.maven;

import io.github.bhuyanp.maven.figlet.Fonts;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;


public abstract class BannerMojoBase extends AbstractMojo implements BannerTextUtil {

    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    MavenProject project;

    @Parameter(property = "bannerText")
    String bannerText;

    @Parameter(property = "bannerFonts")
    String[] bannerFonts;

    @Parameter(property = "captionText",  defaultValue = CAPTION_DEFAULT_VALUE)
    String captionText;

    @Parameter(property = "themePreset", defaultValue = "DARK")
    String themePreset;

}
