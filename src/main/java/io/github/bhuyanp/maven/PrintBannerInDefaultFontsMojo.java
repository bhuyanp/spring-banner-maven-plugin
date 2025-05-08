package io.github.bhuyanp.maven;

import org.apache.maven.api.di.Inject;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.project.MavenProject;

import java.util.Arrays;


/**
 * Mojo to print banner in all default fonts used by the plugin when no banner font is provided.
 */
@Mojo(name = "printBannerInDefaultFonts", threadSafe = true)
public class PrintBannerInDefaultFontsMojo extends BannerMojoBase implements BannerTextUtil {

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        Arrays.stream(DEFAULT_FONTS).toList().forEach(font ->
                System.out.println(getBannerWCaption(project, bannerText, font, captionText, themePreset)
                        + LINE_SEPARATOR
                )
        );
    }
}
