BINDIR=./bin
SRCDIR=./src
DOCDIR=./doc

#get list of all java files in source directory
src_files=$(wildcard $(SRCDIR)/*.java)
#gets list of binary class files from each source file (replaces .java with .class)
out_files=$(src_files:$(SRCDIR)/%.java=$(BINDIR)/%.class)


build: $(out_files)

$(BINDIR)/%.class: $(SRCDIR)/%.java
	javac -d $(BINDIR) -sourcepath $(SRCDIR) $^

clean:
	rm -f ${BINDIR}/*.class

docs:
	javadoc  -classpath ${BINDIR} -d ${DOCDIR} ${SRCDIR}/*.java

cleandocs:
	rm -rf ${DOCDIR}/*

