# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.19

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Disable VCS-based implicit rules.
% : %,v


# Disable VCS-based implicit rules.
% : RCS/%


# Disable VCS-based implicit rules.
% : RCS/%,v


# Disable VCS-based implicit rules.
% : SCCS/s.%


# Disable VCS-based implicit rules.
% : s.%


.SUFFIXES: .hpux_make_needs_suffix_list


# Command-line flag to silence nested $(MAKE).
$(VERBOSE)MAKESILENT = -s

#Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /snap/clion/152/bin/cmake/linux/bin/cmake

# The command to remove a file.
RM = /snap/clion/152/bin/cmake/linux/bin/cmake -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/greg/blaster/shaderlang

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/greg/blaster/shaderlang/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/shaderlang.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/shaderlang.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/shaderlang.dir/flags.make

CMakeFiles/shaderlang.dir/main.c.o: CMakeFiles/shaderlang.dir/flags.make
CMakeFiles/shaderlang.dir/main.c.o: ../main.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/greg/blaster/shaderlang/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building C object CMakeFiles/shaderlang.dir/main.c.o"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles/shaderlang.dir/main.c.o -c /home/greg/blaster/shaderlang/main.c

CMakeFiles/shaderlang.dir/main.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/shaderlang.dir/main.c.i"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E /home/greg/blaster/shaderlang/main.c > CMakeFiles/shaderlang.dir/main.c.i

CMakeFiles/shaderlang.dir/main.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/shaderlang.dir/main.c.s"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S /home/greg/blaster/shaderlang/main.c -o CMakeFiles/shaderlang.dir/main.c.s

# Object files for target shaderlang
shaderlang_OBJECTS = \
"CMakeFiles/shaderlang.dir/main.c.o"

# External object files for target shaderlang
shaderlang_EXTERNAL_OBJECTS =

shaderlang: CMakeFiles/shaderlang.dir/main.c.o
shaderlang: CMakeFiles/shaderlang.dir/build.make
shaderlang: CMakeFiles/shaderlang.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/greg/blaster/shaderlang/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking C executable shaderlang"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/shaderlang.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/shaderlang.dir/build: shaderlang

.PHONY : CMakeFiles/shaderlang.dir/build

CMakeFiles/shaderlang.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/shaderlang.dir/cmake_clean.cmake
.PHONY : CMakeFiles/shaderlang.dir/clean

CMakeFiles/shaderlang.dir/depend:
	cd /home/greg/blaster/shaderlang/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/greg/blaster/shaderlang /home/greg/blaster/shaderlang /home/greg/blaster/shaderlang/cmake-build-debug /home/greg/blaster/shaderlang/cmake-build-debug /home/greg/blaster/shaderlang/cmake-build-debug/CMakeFiles/shaderlang.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/shaderlang.dir/depend

