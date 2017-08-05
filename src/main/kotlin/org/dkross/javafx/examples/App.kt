package org.dkross.javafx.examples

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import java.io.FileNotFoundException
import java.net.URL
import kotlin.system.exitProcess


class App : Application() {

    companion object {
        private const val SCENE_FILENAME_FORMAT = "/%sScene.fxml"
        private const val STAGE_TITLE_FORMAT = "DKrOSS JavaFX Examples - %s"
        private const val USAGE_SCENE_NAME_FORMAT = "\"%s\""

        @JvmStatic
        fun main(args: Array<String>) {
            launch(App::class.java, *args)
        }
    }

    private val usageFormatLines = arrayOf(
            "",
            "Usage:",
            "",
            "javafxexamples <Scene name>",
            "",
            "Available scene names: %s",
            ""
    )
    private val sceneNames = arrayOf(
            "needle",
            "simple"
    )

    private lateinit var sceneName: String
    private lateinit var sceneUrl: URL


    private fun showHelpAndExit() {
        val message = usageFormatLines.joinToString(separator = System.lineSeparator())
                .format(sceneNames.joinToString(
                        transform = {
                            USAGE_SCENE_NAME_FORMAT.format(it)
                        }))

        print(message)
        exitProcess(1)
    }

    override fun init() {
        if (parameters.raw.size != 1) {
            showHelpAndExit()
        }

        sceneName = parameters.raw[0]

        if (!sceneNames.contains(sceneName)) {
            showHelpAndExit()
        }

        val sceneFilename = SCENE_FILENAME_FORMAT.format(sceneName)
        sceneUrl = this::class.java.getResource(sceneFilename) ?: throw FileNotFoundException(sceneFilename)
    }

    override fun start(primaryStage: Stage) {
        primaryStage.title = STAGE_TITLE_FORMAT.format(sceneName)
        primaryStage.scene = Scene(FXMLLoader.load<Parent>(sceneUrl))
        primaryStage.show()
    }
}
