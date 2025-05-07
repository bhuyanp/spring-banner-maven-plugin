package io.github.bhuyanp.maven;

import io.github.bhuyanp.maven.theme.ThemePreset;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.util.Arrays;

@Mojo(name = "generateBanner", defaultPhase = LifecyclePhase.GENERATE_RESOURCES, threadSafe = true)
public class GenerateBannerMojo extends BannerMojoBase implements BannerTextUtil {



    public GenerateBannerMojo() {
        // this constructor is used by maven to create the mojo
    }

    /**
     * This constructor can be used to set all the parameters of the mojo.
     */
    @SuppressWarnings("java:S107")
    public GenerateBannerMojo(final MavenProject project,
                        final String bannerText,
                        final String captionText,
                        final String [] bannerFonts,
                        final String themePreset,
                        final String info,
                        final String font,
                        final String color,
                        final boolean useNbsp) {
        this.project = project;
        this.bannerText = bannerText;
        this.captionText = captionText;
        this.bannerFonts = bannerFonts;
        this.themePreset = themePreset;
    }


    @Override
    public void execute() throws MojoExecutionException, MojoFailureException{
        System.out.println(getBannerWCaption(capitalizeProjectName(bannerText), Arrays.asList(bannerFonts), captionText, ThemePreset.valueOf(themePreset)));
    }
}