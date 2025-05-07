package io.github.bhuyanp.maven;

import io.github.bhuyanp.maven.figlet.Fonts;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name = "printBannerInAllFonts", threadSafe = true)
public class PrintBannerInAllFontsMojo extends BannerMojoBase implements BannerTextUtil {
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        Fonts.all().forEach(font ->
                System.out.println(getBannerWCaption(project, bannerText, font, captionText, themePreset)
                        + LINE_SEPARATOR
                )
        );
    }
}
