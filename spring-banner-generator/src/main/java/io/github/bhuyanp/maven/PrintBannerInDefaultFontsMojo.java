package io.github.bhuyanp.maven;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

import java.util.Arrays;

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
