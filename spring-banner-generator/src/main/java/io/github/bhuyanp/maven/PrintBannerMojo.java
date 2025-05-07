package io.github.bhuyanp.maven;

import io.github.bhuyanp.maven.theme.ThemePreset;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name = "printBanner", defaultPhase = LifecyclePhase.GENERATE_RESOURCES, threadSafe = true)
public class PrintBannerMojo extends BannerMojoBase implements BannerTextUtil {
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException{
        System.out.println(getBannerWCaption(capitalizeProjectName(bannerText), DEFAULT_FONTS, captionText, ThemePreset.valueOf(themePreset)));
    }
}
