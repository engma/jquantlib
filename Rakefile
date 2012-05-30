def java_archives
  @java_archives ||= Dir.glob("lib/**/*.jar")
end

def java_source_directories
  @java_source_directories ||= Dir.glob("*/src/main/java")
end

def java_sources
  return @java_sources if @java_sources
  @java_sources = []
  java_source_directories.each do |dir|
    Dir.glob("#{dir}/**/*.java").each do |java|
      @java_sources << java
    end
  end
  @java_sources
end

def java_classes
  @java_classes ||= Dir.glob("tmp/bin/**/*.class")
end

def bin_directory
  return @bin_directory if @bin_directory
  @bin_directory = File.expand_path("tmp/bin")
  Dir.mkdir @bin_directory unless File.exists?(@bin_directory)
  @bin_directory
end

desc "Compile sources"
task :compile do
  system "javac -classpath #{java_archives.join(':')} -sourcepath #{java_source_directories.join(':')} -d #{bin_directory} #{java_sources.join(' ')}"
end