import os
import subprocess
import re
from datetime import datetime
#from dateutil.parser import parse
def get_video_metadata(file_path):
    try:
        cmd = [r'C:\Users\mdzhs\OneDrive\Documents\Programming Projects\MovieLibraryViewer\MovieLibraryViewer\python\exiftool.exe', file_path]
        result = subprocess.run(cmd, capture_output=True, text=True)
        metadata = result.stdout
        return metadata
    except FileNotFoundError as e:
        print("Error:", e)
        return None

def extract_metadata_for_directory(video_directory,mode):
    #*** List all files in the directory
    files = os.listdir(video_directory)

    #** Filter out video files (you can customize this based on your video file extensions)
    video_files = [file for file in files if file.endswith('.mp4')]

    #*** Assuming desired_fields are correctly defined here
    desired_fields = ['File Name                       ', 'File Creation Date/Time         ', 'Genre                           ', 'Comment                         ', 'Category                        ', 'Artist                          ']

    for video_file in video_files:
        video_name = os.path.splitext(video_file)[0]
        video_file_path = os.path.join(video_directory, video_file)
        output_file_path = os.path.join(video_directory, f"{video_name}.txt")

        if mode == 'no_overwrite' and os.path.exists(output_file_path):
            print(f"Skipping {video_name}.txt (already exists)")
            continue

        print(f"Creating {video_name}.txt")
        with open(output_file_path, 'w') as file:
            metadata = get_video_metadata(video_file_path)
            if metadata:
                metadata_lines = metadata.splitlines()
                desired_metadata_values = []

                for line in metadata_lines:
                    for field in desired_fields:
                        if line.startswith(field + ':'):
                            metadata_value = line.split(":", 1)[1].strip()
                            desired_metadata_values.append(metadata_value)

                for value in desired_metadata_values:
                    # Parse the date using string manipulation
                    if '+' in value:
                        value, timezone = value.split('+', 1)
                        timezone = '+' + timezone
                    else:
                        timezone = ''

                    try:
                        date_obj = datetime.strptime(value, '%Y:%m:%d %H:%M:%S')
                        formatted_date = date_obj.strftime('%d/%m/%Y %H:%M:%S') + timezone
                        file.write(formatted_date + '\n')
                    except ValueError:
                        file.write(value + '\n')

#*** Example usage for a directory
video_directory = r'C:\\My_Data\\Movies'
#*** The output_folder argument is no longer needed
extract_metadata_for_directory(video_directory,'no_overwrite')
