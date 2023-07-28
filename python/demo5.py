import os
import subprocess


def get_video_metadata(file_path):
    try:
        cmd = [r'C:\Users\mdzhs\OneDrive\Documents\Programming Projects\MovieLibraryViewer\MovieLibraryViewer\python\exiftool.exe', file_path]
        result = subprocess.run(cmd, capture_output=True, text=True)
        metadata = result.stdout
        return metadata
    except FileNotFoundError as e:
        print("Error:", e)
        return None

def extract_metadata_for_directory(directory):
    # List all files in the directory
    files = os.listdir(directory)

    # Filter out video files (you can customize this based on your video file extensions)
    video_files = [file for file in files if file.endswith('.mp4')]

    # Assuming desired_fields are correctly defined here
    desired_fields = ['File Name                       ', 'File Creation Date/Time         ', 'Genre                           ', 'Comment                         ', 'Category                        ', 'Artist                          ']

    # Create a folder to store the text files
    output_folder = os.path.join(directory, 'metadata_files')
    os.makedirs(output_folder, exist_ok=True)

    for video_file in video_files:
        video_name = os.path.splitext(video_file)[0]

        # Proceed to create the .txt file
        print(f"Creating {video_name}.txt")
        video_file_path = os.path.join(directory, video_file)
        metadata = get_video_metadata(video_file_path)
        if metadata:
            metadata_lines = metadata.splitlines()
            desired_metadata_values = []

            for line in metadata_lines:
                for field in desired_fields:
                    if line.startswith(field + ':'):
                        metadata_value = line.split(":")[1].strip()
                        desired_metadata_values.append(metadata_value)

            file_path = os.path.join(output_folder, f"{video_name}.txt")
            with open(file_path, 'w') as file:
                for value in desired_metadata_values:
                    file.write(value + '\n')

def update_video_metadata_from_txt(directory, output_folder):
    for txt_file in os.listdir(output_folder):
        if txt_file.endswith(".txt"):
            video_name = os.path.splitext(txt_file)[0]
            video_file_path = os.path.join(directory, video_name + ".mp4")
            txt_file_path = os.path.join(output_folder, txt_file)

            with open(txt_file_path, 'r') as txt_file:
                metadata_values = txt_file.readlines()

            with exiftool.ExifTool() as et:
                metadata = {f"{field.strip()}:": value.strip() for field, value in zip(desired_fields, metadata_values)}
                et.execute(b"-overwrite_original", *[f"-{field.strip()}={value.strip()}" for field, value in metadata.items()])
                et.execute(video_file_path)

# Example usage for a directory
video_directory = r'C:\\My_Data\\Movies'
output_folder = r'C:\\My_Data\\Movies\\metadata_files'
update_video_metadata_from_txt(video_directory, output_folder)
extract_metadata_for_directory(video_directory, output_folder)
