package io.github.bhuyanp.maven;

import io.github.bhuyanp.maven.theme.ThemePreset;
import org.apache.maven.api.di.Inject;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.project.MavenProject;

import java.util.Arrays;

/**
 * Mojo to print banner with all the current configurations.
 */
@Mojo(name = "printBanner", threadSafe = true)
public class PrintBannerMojo extends BannerMojoBase implements BannerTextUtil {

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException{
        String bannerWCaption = getBannerWCaption(project, bannerText, Arrays.asList(bannerFonts), captionText, themePreset);
        System.out.println(bannerWCaption);
    }
}
