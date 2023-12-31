package fr.umlv.template;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Document<E extends Record> {
  private final Template template;
  private final List<Function<? super E, ?>> functions;

  public Document(Template template, List<? extends Function<? super E, ?>> functions) {
    Objects.requireNonNull(template);
    Objects.requireNonNull(functions);

    if (functions.size() + 1 != template.fragments.size()) {
      throw new IllegalArgumentException();
    }

    this.template = template;
    this.functions = List.copyOf(functions);
  }

  /* Package */
  String applyTemplate(E record) {
    Objects.requireNonNull(record);
    return this.template.interpolate(functions.stream().map(e -> e.apply(record)).toList());
  }

  public String generate(List<? extends E> records, String separator) {
    Objects.requireNonNull(records);
    Objects.requireNonNull(separator);
    return records.stream()
            .map(this::applyTemplate)
            .collect(Collectors.joining(separator));
  }

  public record Template(List<String> fragments) {
    public Template(List<String> fragments) {
      Objects.requireNonNull(fragments);
      if (fragments.isEmpty()) {
        throw new IllegalArgumentException("fragments is empty");
      }

      this.fragments = List.copyOf(fragments);
    }

    public <T> String interpolate(List<T> values) {
      Objects.requireNonNull(values);
      if (values.size() + 1 != fragments.size()) {
        throw new IllegalArgumentException();
      }

      var stringBuilder = new StringBuilder();
      var fragmentsIterator = fragments.iterator();

      for (T value : values) {
        stringBuilder.append(fragmentsIterator.next()).append(value);
      }

      return stringBuilder.append(fragmentsIterator.next()).toString();
    }

    public static Template of(String value) {
      Objects.requireNonNull(value);

      var template = new ArrayList<String>();
      int start = 0;
      for (int i = 0; i < value.length(); i++) {
        if (value.charAt(i) == '@') {
          template.add(value.substring(start, i));
          start = i + 1;
        }
      }

      template.add(value.substring(start));
      return new Template(template);
    }

    @SafeVarargs
    public final <E extends Record> Document<E> toDocument(Function<? super E, ?>... functions) {
      Objects.requireNonNull(functions);
      return new Document<>(this, List.of(functions));
    }

    public <E> Template bind(E value) {
      /* Objects.requireNonNull(value); - It must allow null values */

      var binder = Stream.concat(Stream.of(
              fragments.get(0)
                      .concat(String.valueOf(value))
                      .concat(fragments.get(1))), fragments.subList(2, fragments.size())
              .stream()).toList();

      return new Template(binder);
    }

    @Override
    public String toString() {
      return String.join("@", fragments);
    }
  }
}
