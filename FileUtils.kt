package com.omx1000.util

import android.content.Context
import android.media.MediaScannerConnection
import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object FileUtils {

    fun writeDataToFile(mContext: Context, folderName:String, fileName:String, data:String)
    {
        if(data.isNotEmpty())
        {
            val folder = mContext.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).toString() + File.separator + folderName

            val file1 = File(folder)
            if (!file1.exists())
            {
                file1.mkdirs()
            }

            // Storing the data in file with name as param file name.
            val file = File(file1.toString(), "$fileName.txt")

            var fileOutputStream: FileOutputStream? = null
            try
            {
                fileOutputStream = FileOutputStream(file)
                fileOutputStream.write(data.toByteArray())

                //Refresh the storage.
                MediaScannerConnection.scanFile(mContext, arrayOf(file.toString()),null, null)
            }
            catch (e: java.lang.Exception)
            {
                e.printStackTrace()
            }
            finally
            {
                if (fileOutputStream != null)
                {
                    try
                    {
                        fileOutputStream.close()
                    }
                    catch (e: IOException)
                    {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

}